import dto.UserDTO;
import network.Server;
import persistence.MyBatisConnectionFactory;
import persistence.dao.UserDAO;
import service.UserService;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String args[]) {
        ApartRecommendationProgram program = new ApartRecommendationProgram();
        program.run();
    }
}