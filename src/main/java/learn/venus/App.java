package learn.venus;

import learn.venus.data.OrbiterFileRepository;
import learn.venus.domain.OrbiterService;
import learn.venus.models.Orbiter;
import learn.venus.ui.Controller;
import learn.venus.ui.View;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);

//        OrbiterFileRepository repository =
//                new OrbiterFileRepository("./data/orbiters.csv");
//
//        OrbiterService service = new OrbiterService(repository);
//
//        View view = new View();
//
//        Controller controller = new Controller(service, view);
//        controller.run();

    }
}
