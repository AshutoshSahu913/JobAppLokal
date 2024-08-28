package com.example.jobapplokal.utils

const val BASE_URL="https://testapi.getlokalapp.com"


sealed class ResultState<out T> {
    class Success<T>(var data: T) : ResultState<T>()
    class Error(var exception: Throwable) : ResultState<Nothing>()
    data object Loading : ResultState<Nothing>()
}


fun getStateNameByAbbreviation(abbreviation: String): String? {
    val stateMap = mapOf(
        "AP" to "Andhra Pradesh",
        "AR" to "Arunachal Pradesh",
        "AS" to "Assam",
        "BR" to "Bihar",
        "CG" to "Chhattisgarh",
        "GA" to "Goa",
        "GJ" to "Gujarat",
        "HR" to "Haryana",
        "HP" to "Himachal Pradesh",
        "JK" to "Jammu and Kashmir",
        "JH" to "Jharkhand",
        "KA" to "Karnataka",
        "KL" to "Kerala",
        "MP" to "Madhya Pradesh",
        "MH" to "Maharashtra",
        "MN" to "Manipur",
        "ML" to "Meghalaya",
        "MZ" to "Mizoram",
        "NL" to "Nagaland",
        "OD" to "Odisha",
        "PB" to "Punjab",
        "RJ" to "Rajasthan",
        "SK" to "Sikkim",
        "TN" to "Tamil Nadu",
        "TG" to "Telangana",
        "TR" to "Tripura",
        "UP" to "Uttar Pradesh",
        "UT" to "Uttarakhand",
        "WB" to "West Bengal",
        "AN" to "Andaman and Nicobar Islands",
        "CH" to "Chandigarh",
        "DN" to "Dadra and Nagar Haveli and Daman and Diu",
        "DL" to "Delhi",
        "LD" to "Lakshadweep",
        "PY" to "Puducherry"
    )

    // Convert abbreviation to uppercase to make lookup case-insensitive
    return stateMap[abbreviation]
}
