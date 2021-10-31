package bartosz.salwiczek.lotnisko;

import bartosz.salwiczek.lotnisko.plane.entity.Airplane;
import bartosz.salwiczek.lotnisko.plane.entity.AirplaneModel;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneModelService;
import bartosz.salwiczek.lotnisko.plane.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Scanner;

@Component
public class CommandLine implements CommandLineRunner {
    private AirplaneService airplaneService;
    private AirplaneModelService airplaneModelService;

    @Autowired
    public CommandLine(AirplaneService airplaneService, AirplaneModelService airplaneModelService) {
        this.airplaneService = airplaneService;
        this.airplaneModelService = airplaneModelService;
    }

    @Override
    public void run(String... args) throws Exception {

        Scanner in = new Scanner(System.in);

        while(true) {
            String input = in.nextLine();
            if(input.equals("help")) {
                handleHelp();
            }
            if(input.equals("categories")) {
                handleCategories();
            }
            if(input.equals("elements")) {
                handleElements();
            }
            if(input.equals("add")) {
                handleAdd(in);
            }
            if(input.equals("delete")) {
                handleDelete(in);
            }
            if(input.equals("quit")) {
                break;
            }
        }

    }

    private void handleHelp() {
        System.out.println("help - get help");
        System.out.println("categories - list categories");
        System.out.println("elements - list elements");
        System.out.println("add - add element");
        System.out.println("delete - delete element");
        System.out.println("quit - quit application");
    }

    private void handleCategories() {
        airplaneModelService.findAll().forEach(System.out::println);
    }

    private void handleElements() {
        airplaneService.findAll().forEach(System.out::println);
    }

    private void handleAdd(Scanner in) {
        System.out.println("Enter airplane model name:");
        String modelName = in.next();
        Optional<AirplaneModel> airplaneModel = airplaneModelService.find(modelName);
        if(airplaneModel.isEmpty()) {
            System.out.println("No airplane model with specified model name found");
            return;
        }

        System.out.println("Enter mileage:");
        int mileage = in.nextInt();

        Airplane airplane = Airplane.builder().airplaneModel(airplaneModel.get()).mileage(mileage).build();

        airplaneService.create(airplane);

        System.out.println("Created airplane.");
    }

    private void handleDelete(Scanner in) {
        System.out.println("Enter serial number:");
        Long serialNumber = in.nextLong();
        Optional<Airplane> airplane = airplaneService.find(serialNumber);
        if(airplane.isPresent()) {
            airplaneService.delete(airplane.get());
        } else {
            System.out.println("Cannot find plane with specified serial number");
        }

        System.out.println("Delete airplane.");
    }
}
