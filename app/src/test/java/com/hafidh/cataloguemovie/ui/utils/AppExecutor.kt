package com.hafidh.cataloguemovie.ui.utils

import com.hafidh.cataloguemovie.utils.AppExecutors
import java.util.concurrent.Executor

class AppExecutor: AppExecutors(execute, execute) {
    companion object {
        private val execute = Executor{ execute -> execute.run() }
    }
}