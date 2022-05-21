import network.Server;
import persistence.MyBatisConnectionFactory;

public class ApartRecommendationProgram {

    private final Server server;
    public ApartRecommendationProgram() {

        server = new Server();
    }

    public void run() {
        server.run();
    }
}
