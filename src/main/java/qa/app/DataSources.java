package qa.app;


import qa.model.TestData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;


public class DataSources {

    public static Iterator<Object> practiceFormTestData() {
        List<Object> list = new ArrayList<>();
        Iterator<String[]> rows = ExcelReader.getData("Practice Form Test Data", "data");
        while (rows.hasNext()) {
            String[] cells = rows.next();
            list.add(new Object[]{new TestData()
                    .setFirstName(cells[1])
                    .setLastName(cells[2])
                    .setEmail(cells[3])
                    .setGender(cells[4])
                    .setDateOfBirth(cells[5])
                    .setSubjects(new ArrayList<>(Arrays.stream(cells[6].split(",")).collect(Collectors.toList())))
                    .setMobile(cells[7])
                    .setHobbies(new ArrayList<>(Arrays.stream(cells[8].split(",")).collect(Collectors.toList())))
                    .setAddress(cells[9])
                    .setState(cells[10])
                    .setCity(cells[11])
            });
        }
        return list.iterator();
    }
}
