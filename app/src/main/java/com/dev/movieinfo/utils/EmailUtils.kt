package com.dev.movieinfo.utils

import java.util.regex.Pattern

public class EmailUtils {
    companion object{
        val EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                    "\\@" +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                    "(" +
                    "\\." +
                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                    ")+"
        )

        fun isValidEmail(str: String): Boolean{
            if(str.isEmpty())
                return false
            return EMAIL_ADDRESS_PATTERN.matcher(str).matches()
        }
    }
}