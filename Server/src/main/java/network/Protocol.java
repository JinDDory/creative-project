package network;

public class Protocol {

    public static final int UNDEFINED = 0;
    // TYPE
    public static final int TYPE_REQUEST = 1;
    public static final int TYPE_RESPONSE = 2;

    // CODE : TYPE_REQUEST_CODE
    public static final int T1_CODE_CREATE = 1;
    public static final int T1_CODE_READ = 2;
    public static final int T1_CODE_UPDATE = 3;
    public static final int T1_CODE_DELETE = 4;
    public static final int T1_CODE_LOGIN = 5;
    public static final int T1_CODE_LOGOUT = 6;

    // CODE : TYPE_RESPONSE_CODE
    public static final int T2_CODE_SUCCESS = 1;
    public static final int T2_CODE_FAIL = 2;

    // ENTITY
    public static final int ENTITY_USER = 1;
    public static final int ENTITY_ADMIN = 2;
    public static final int ENTITY_MEMBER = 3;
    public static final int ENTITY_APART_TRANSACTION = 4;
    public static final int ENTITY_PRICE_INDICES = 5;
    public static final int ENTITY_FAVORITES = 6;

    // READ_OPTION
    public static final int READ_ALL = 1;
    public static final int READ_BY_ID = 2;
    public static final int READ_BY_OPTION = 3;


    // LENGTH
    public static final int LEN_TYPE = 1;
    public static final int LEN_CODE = 1;
    public static final int LEN_ENTITY = 1;
    public static final int LEN_READ_OPTION = 1;
    public static final int LEN_HEADER = LEN_TYPE + LEN_CODE + LEN_ENTITY + LEN_READ_OPTION;


}
