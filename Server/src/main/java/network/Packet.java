package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Packet {

    private byte type;
    private byte code;
    private byte entity;
    private byte readOption;
    private ArrayList<Object> body;

    public Packet() {
        this(Protocol.UNDEFINED, Protocol.UNDEFINED, Protocol.UNDEFINED);
    }

    public Packet(int type) {
        this(type, Protocol.UNDEFINED, Protocol.UNDEFINED);
    }

    public Packet(int type, int code) {
        this(type, code, Protocol.UNDEFINED);
    }

    public Packet(int type, int code, int entity) {
        this(type, code, entity, Protocol.UNDEFINED);
    }

    public Packet(int type, int code, int entity, int readOption){
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

    public ArrayList<Object> getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = (ArrayList<Object>) body;
    }

    public void setBody(ArrayList<Object> body) {
        this.body = body;
    }

    // 패킷 받은거 읽어들이기
    public Packet read(ObjectInputStream is) throws Exception {
        readHeader(is);
        readBody(is);
        return this;
    }

    private void readHeader(ObjectInputStream is) throws Exception {
        byte[] header = new byte[Protocol.LEN_HEADER];

        try {
            // 헤더 읽어들인 후 헤더 초기화
            is.read(header, 0, Protocol.LEN_HEADER);
            type = header[0];
            if (this.type == Protocol.UNDEFINED) {
                throw new Exception("통신 오류 > 연결 오류");
            }
            code = header[Protocol.LEN_TYPE];
            entity = header[Protocol.LEN_TYPE + Protocol.LEN_CODE];
            readOption = header[Protocol.LEN_TYPE + Protocol.LEN_CODE + Protocol.LEN_ENTITY];

        } catch (IOException e) {
            e.printStackTrace();
            throw new IOException("통신 오류 > 데이터 수신 실패");
        }
    }

    private void readBody(ObjectInputStream is) throws IOException, ClassNotFoundException {
        body = (ArrayList<Object>) is.readObject();
    }

    // 패킷 전송
    public void send(ObjectOutputStream os) throws IOException {
        os.write(makeHeader());
        os.writeObject(body);
        os.flush();
        System.out.println("Send to Client");
    }


    // 패킷을 생성하여 리턴
    private byte[] makeHeader() {
        byte[] header = new byte[Protocol.LEN_HEADER];
        header[0] = type;
        header[Protocol.LEN_TYPE] = code;
        header[Protocol.LEN_TYPE + Protocol.LEN_CODE] = entity;
        header[Protocol.LEN_TYPE + Protocol.LEN_CODE + Protocol.LEN_ENTITY] = readOption;
        return header;
    }

//
//    private byte[] intToByte(int i) {
//        return ByteBuffer.allocate(Integer.SIZE / 8).putInt(i).array();
//    }
//
//    private int byteToInt(byte[] b) {
//        return ByteBuffer.wrap(b).getInt();
//    }
}
