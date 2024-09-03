package com.bestswlkh0310.booster.api.core.jpa

import org.springframework.transaction.annotation.Transactional

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Transactional(readOnly = true, rollbackFor = [Exception::class])
annotation class ReadOnlyTransactional