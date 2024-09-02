import Alamofire
import MyMoya
import UIKit
import Foundation
import SwiftUI
import Combine
import Moya

final class TokenInterceptor: Moya.RequestInterceptor {
    
    private var subscriptions = Set<AnyCancellable>()
    
    init() {}
    
    func adapt(_ urlRequest: URLRequest, for session: Session, completion: @escaping (Swift.Result<URLRequest, Error>) -> Void) {
        
        var modifiedRequest = urlRequest
        guard let accessToken: String = MySign.accessToken else {
            completion(.success(urlRequest))
            return
        }
        print("✅ AuthInterceptor - Set token: \(accessToken)")
        modifiedRequest.setValue("Bearer " + accessToken, forHTTPHeaderField: "Authorization")
        completion(.success(modifiedRequest))
    }
    
    func retry(_ request: Request, for session: Session, dueTo error: Error, completion: @escaping (RetryResult) -> Void) {
        
        print("✅ AuthInterceptor - Start refresh")
        guard request.retryCount <= 3 else {
            print("❌ AuthInterceptor - RetryCount가 3보다 큽니다")
            return
        }
        
        guard let url = request.request?.url else {
            print("❌ AuthInterceptor - Invalid URL")
            completion(.doNotRetryWithError(error))
            return
        }
        print("✅ AuthInterceptor - URL String: \(url.absoluteString)")
        
        guard let response = request.task?.response as? HTTPURLResponse else {
            print("❌ AuthInterceptor - Invalid Response")
            completion(.doNotRetryWithError(error))
            return
        }
        print("✅ AuthInterceptor - StatusCode: \(response.statusCode)")
        
        let refreshStatusCode = [403, 401]
        guard refreshStatusCode.contains(response.statusCode) else {
            print("❌ AuthInterceptor - Invalid StatusCode")
            completion(.doNotRetryWithError(error))
            return
        }
        
        guard let refreshToken = MySign.refreshToken else {
            print("❌ AuthInterceptor - Refresh Token is Empty")
            MySign.logout()
            completion(.doNotRetryWithError(APIError<ErrorRes>.refreshFailure))
            return
        }
        
        print("✅ AuthInterceptor - Try refresh with token - \(refreshToken)")
        AuthService.shared.refresh(
            .init(refreshToken: refreshToken)
        ).success { res in
            print("✅ AuthInterceptor - Refresh Success")
            let accessToken = res.data.accessToken
            MySign.reissue(accessToken)
            completion(.retry)
        }.failure { err in
            MySign.logout()
            print("❌ AuthInterceptor - Refresh Failure")
            completion(.doNotRetryWithError(APIError<ErrorRes>.refreshFailure))
        }.observe(&subscriptions)
    }
    
    deinit {
        subscriptions.forEach { $0.cancel() }
    }
}
