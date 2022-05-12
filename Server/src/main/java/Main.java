import dto.UserDTO;
import persistence.MyBatisConnectionFactory;
import persistence.dao.UserDAO;
import service.UserService;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) {

//        Scanner sc = new Scanner(System.in);
//        String id, password, name, email;
//
//        UserDAO userDAO = new UserDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        UserService userService = new UserService(userDAO);
//        UserDTO userDTO;
//        List<UserDTO> userDTOList = null;


        // 사용자 등록
//        System.out.println("사용자 등록");
//
//        id = "leb001122";
//        password = "1234";
//        name = "이은빈";
//        email = "leb001122@naver.com";
//
//        userDTO = UserDTO.builder()
//                .userId(id)
//                .userPassword(password)
//                .userName(name)
//                .userEmail(email)
//                .build();
//        userService.insertUser(userDTO);


        // 사용자 전체 조회
//        selectAllUser(userDTOList, userService);
//
//
//        // 사용자 수정
//        System.out.println("사용자 정보 수정");
//
//        id = "leb001122";
//        password = "11111111";
//        name = null;
//        email = null;
//
//        userDTO = UserDTO.builder()
//                .userId(id)
//                .userPassword(password)
//                .userName(name)
//                .userEmail(email)
//                .build();
//        userService.updateUser(userDTO);
//
//        selectAllUser(userDTOList, userService);
//
//
        // 사용자 삭제
//        System.out.println("사용자 삭제");
//
//        id = "leb001122";
//
//        userDTO = UserDTO.builder()
//                .userId(id)
//                .build();
//        userService.deleteUser(userDTO);
//
//        selectAllUser(userDTOList, userService);

        // 아이디로 사용자 조회
//        System.out.println("아이디로 사용자 조회");
//
//        id = "eunbin";
//
//        userDTO = UserDTO.builder()
//                .userId(id)
//                .build();
//        userDTO = userService.selectUserById(userDTO);
//
//        printUser(userDTO);


//        ApartInfoDAO apartInfoDAO = new ApartInfoDAO(MyBatisConnectionFactory.getSqlSessionFactory());
//        ApartInfoService apartInfoService = new ApartInfoService(apartInfoDAO);
//        ApartInfoDTO apartInfoDTO;
//        List<ApartInfoDTO> apartInfoDTOList = null;
//
//        int apartId = 4;
//        String city = "강원도 강릉시 견소동";
//        String road = "경강로2539번길 8";
//        String danji = "송정한신";
//        String area = "59.8";
//        String floor = "15";
//        String builtYear = "1997";
//
//        // 아파트 정보 등록
//        apartInfoDTO = ApartInfoDTO.builder()
//                .apartInfoId(apartId)
//                .siGunGu(city)
//                .roadName(road)
//                .danjiName(danji)
//                .area(area)
//                .floor(floor)
//                .buildYear(builtYear)
//                .build();
//        apartInfoService.insertApartInfo(apartInfoDTO);
//
//        // 아파트 정보 전체 조회
//        selectAllApart(apartInfoDTOList, apartInfoService);



    }


//
//    private static void printApart(ApartInfoDTO dto) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("pk : " + dto.getApartInfoId() + " | ");
//        builder.append("시군구 : " + dto.getSiGunGu() +" | ");
//        builder.append("도로명 : " + dto.getRoadName() +" | ");
//        builder.append("단지 : " + dto.getDanjiName() +" | ");
//        builder.append("면적(m^2) : " + dto.getArea() +" | ");
//        builder.append("건축년도 : " + dto.getFloor() +" | ");
//        builder.append("층 : " + dto.getBuildYear() +" | \n");
//        builder.append("*******************************");
//        System.out.println(builder.toString());
//    }
//
//    private static void printApartList(List<ApartInfoDTO> dtoList) {
//        System.out.println("---------------------------------------아파트 정보 조회---------------------------------------");
//        for (ApartInfoDTO apartInfoDTO : dtoList) {
//            printApart(apartInfoDTO);
//        }
//    }
//
//    private static void selectAllApart(List<ApartInfoDTO> dtoList, ApartInfoService apartInfoService) {
//        System.out.println("등록된 사용자 전체 조회");
//        dtoList = apartInfoService.selectAll();
//
//        if (dtoList == null) {
//            System.out.println("사용자가 아직 없어요~");
//        }
//        else {
//            printApartList(dtoList);
//        }
//    }


//    private static void printUserList(List<UserDTO> userDTOList) {
//        System.out.println("---------------------------------------사용자 조회---------------------------------------");
//        for (UserDTO userDTO : userDTOList) {
//            printUser(userDTO);
//        }
//    }
//
//    private static void printUser(UserDTO userDTO) {
//        StringBuilder builder = new StringBuilder();
//        builder.append("아이디 : " + userDTO.getUserId() +" | ");
//        builder.append("비밀번호 : " + userDTO.getUserPassword() +" | ");
//        builder.append("이름 : " + userDTO.getUserName() +" | ");
//        builder.append("이메일 : " + userDTO.getUserEmail() +" | \n");
//        builder.append("*******************************");
//        System.out.println(builder.toString());
//    }
//
//    private static void selectAllUser(List<UserDTO> userDTOList, UserService userService) {
//        System.out.println("등록된 사용자 전체 조회");
//        userDTOList = userService.selectAll();
//
//        if (userDTOList == null) {
//            System.out.println("사용자가 아직 없어요~");
//        }
//        else {
//            printUserList(userDTOList);
//        }
//    }

}