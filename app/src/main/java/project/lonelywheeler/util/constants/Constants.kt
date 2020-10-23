package project.lonelywheeler.util.constants

const val INTENT_REQUEST_CODE_IMAGE = 1;
const val RESPONSE_CODE_REQUEST_SUCCESS = 200;
const val RESPONSE_CODE_REQUEST_FAIL = 201;
const val RESPONSE_CODE_SERVER_FAIL = 202;

const val RESOLUTION_1080X768 = 786432

const val ENTITY_TYPE_MOTOR_VEHICLE = 1
const val ENTITY_TYPE_PEDESTRIAN_VEHICLE = 2
const val ENTITY_TYPE_EQUIPMENT = 3

val REGEX_USERNAME = Regex("^([a-zA-Z])+([\\w]{2,})+$")
val REGEX_EMAIL = Regex("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$")
val REGEX_FIRST_NAME = Regex("[A-Z][a-z]*")
val REGEX_LAST_NAME = Regex("[a-zA-z]+([ '-][a-zA-Z]+)*")
val REGEX_CITY_STREET = Regex("([a-zA-Z]+|[a-zA-Z]+\\\\s[a-zA-Z]+)")
val REGEX_MOBILE_NUMBER = Regex("^(\\\\d{3}[- .]?){2}\\\\d{4}\$")