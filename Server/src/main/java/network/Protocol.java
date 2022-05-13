package network;

import org.apache.commons.lang3.SerializationUtils;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class Protocol {

    public static final int UNDEFINED = 0;
    // TYPE
    public static final int TYPE_REQUEST = 1;
    public static final int TYPE_RESPONSE = 2;

    // CODE : TYPE_REQUEST_CODE
    public static final int T1_CODE_CREATE = 1;
    public static final int T1_CODE_INSERT = 2;
    public static final int T1_CODE_UPDATE = 3;
    public static final int T1_CODE_DELETE = 4;
    public static final int T1_CODE_LOGIN = 5;
    public static final int T1_CODE_LOGOUT = 6;
    public static final int T1_CODE_APART_RECOMMEND = 7;
    public static final int T1_CODE_INDICES_GRAPH = 8;

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
    public static final int LEN_OBJECT_LENGTH_ARR_LENGTH = 4;
    public static final int LEN_OBJECT_LENGTH_ARR = 4;

    public static final int LEN_HEADER = LEN_TYPE + LEN_CODE + LEN_ENTITY + LEN_READ_OPTION + LEN_OBJECT_LENGTH_ARR_LENGTH;

    //header
    private byte type;
    private byte code;
    private byte entity;
    private byte readOption;
    private int objectLengthArrLength; // objectLengthArr의 길이(개수)
    // body
    private int[] objectLengthArr; // 각 객체들의 크기 배열
    private int bodyLength;
    private byte[] body;

    public Protocol() {
        this(UNDEFINED, UNDEFINED, UNDEFINED);
    }

    public Protocol(int type) {
        this(type, UNDEFINED, UNDEFINED);
    }

    public Protocol(int type, int code) {
        this(type, code, UNDEFINED);
    }

    public Protocol(int type, int code, int entity) {
        this(type, code, entity, UNDEFINED);
    }

    public Protocol(int type, int code, int entity, int readOption){
        setType(type);
        setCode(code);
        setEntity(entity);
        setReadOption(readOption);
    }

    public byte getType() {
        return type;
    }

    public void setType(int type) {
        this.type = (byte) type;
    }

    public byte getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = (byte) code;
    }

    public byte getEntity() {
        return entity;
    }

    public void setEntity(int entity) {
        this.entity = (byte) entity;
    }

    public byte getReadOption(){
        return readOption;
    }

    public void setReadOption(int readOption){
        this.readOption = (byte) readOption;
    }

    public int getBodyLength() {
        return bodyLength;
    }

    public void setBodyLength() {
        for (int i = 0; i < objectLengthArr.length; i++) {
            bodyLength += objectLengthArr[i];
        }
    }

    public byte[] getBody() {
        return body;
    }

    public void setBody(byte[] body) {
        this.body = body;
    }


    // 보낼 data - 객체 배열을 직렬화하여 body 초기화
    public void setObject(Object object) {
        byte[] serializedObject = SerializationUtils.serialize((Serializable) object);
        System.arraycopy(serializedObject, 0, body, bodyLength, serializedObject.length);
        objectLengthArr[objectLengthArr.length] = serializedObject.length;
        bodyLength = serializedObject.length;
    }

    public void setObjectArray(Object[] objects) {
        for (Object obj : objects) {
            byte[] serializedObject = SerializationUtils.serialize((Serializable) obj);
            System.arraycopy(serializedObject, 0, body, bodyLength, serializedObject.length);
            objectLengthArr[objectLengthArr.length] = serializedObject.length;
            bodyLength += serializedObject.length;
        }
    }

    // 받은 data - body의 데이터를 객체로 역직렬화하여 return
    public Object getObject(){
        Object object;
        byte[] buf = new byte[objectLengthArr[0]];
        System.arraycopy(body, 0, buf, 0, objectLengthArr[0]);
        object = SerializationUtils.deserialize(buf);
        return object;
    }

    public Object getObjectArray(){
        Object[] objects = new Object[objectLengthArr.length];
        for (int i = 0, pos = 0; i < objectLengthArr.length; i++) {
            byte[] buf = new byte[objectLengthArr[i]];
            System.arraycopy(body, pos, buf, 0, objectLengthArr[i]);
            pos += objectLengthArr[i];
            objects[i] = SerializationUtils.deserialize(buf);
        }
        return objects;
    }


    // 패킷 전송
    public void send(ObjectOutputStream os) throws IOException {
        os.write(getPacket());
        os.flush();
        System.out.println("Send to Client");
    }

    // 패킷 읽어들이기
    public Protocol read(InputStream is) throws Exception {
        byte[] header = new byte[Protocol.LEN_HEADER];

        try {
            // 헤더 읽어들인 후 헤더 초기화
            is.read(header, 0, Protocol.LEN_HEADER);
            setHeader(header);

            if (this.type == Protocol.UNDEFINED){
                throw new Exception("통신 오류 > 연결 오류");
            }

            if (bodyLength > 0) {
                byte[] buf = new byte[bodyLength];
                is.read(buf, 0, bodyLength);
                body = buf;
            }
            return this;
        }
        catch (IOException e) {
            e.printStackTrace();
            throw new IOException("통신 오류 > 데이터 수신 실패");
        }
    }

    // 패킷을 생성하여 리턴 (패킷 전송시 사용)
    private byte[] getPacket() {
        byte[] packet = new byte[LEN_HEADER + bodyLength];
        packet[0] = type;
        packet[LEN_TYPE] = code;
        packet[LEN_TYPE + LEN_CODE] = entity;
        packet[LEN_TYPE+LEN_CODE+LEN_ENTITY] = readOption;
        System.arraycopy(intToByte(bodyLength), 0, packet,
                LEN_TYPE + LEN_CODE + LEN_ENTITY + LEN_READ_OPTION, LEN_BODY_LENGTH);
        if (bodyLength > 0)rraycopy(body, 0, packet, LEN_HEADER, bodyLength);
        return packe
            System.at;
    }

    private void setHeader(byte[] packet) {
        type = packet[0];
        code = packet[LEN_TYPE];
        entity = packet[LEN_TYPE + LEN_CODE];
        readOption = packet[LEN_TYPE + LEN_CODE + LEN_ENTITY];
        byte[] bytes = new byte[LEN_OBJECT_LENGTH_ARR_LENGTH];
        System.arraycopy(packet, LEN_TYPE + LEN_CODE + LEN_ENTITY+LEN_READ_OPTION, bytes, 0, LEN_OBJECT_LENGTH_ARR);
        objectLengthArrLength = byteToInt(bytes);

        byte[] lengthArr = new byte[Byte.SIZE * objectLengthArrLength];
        System.arraycopy(packet, LEN_TYPE + LEN_CODE + LEN_ENTITY + LEN_READ_OPTION + LEN_OBJECT_LENGTH_ARR,
                lengthArr, 0, Byte.SIZE * objectLengthArrLength);

        for (int i = 0; i < objectLengthArrLength; i += Byte.SIZE) {
            byte[] lengthOne = new byte[Byte.SIZE];
            System.arraycopy(lengthArr, i, lengthOne, 0, Byte.SIZE);
            objectLengthArr[i] = byteToInt(lengthOne);
        }
        setBodyLength();
    }


    private byte[] intToByte(int i) {
        return ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array();
    }

    private int byteToInt(byte[] b) {
        return ByteBuffer.wrap(b).getInt();
    }
}
