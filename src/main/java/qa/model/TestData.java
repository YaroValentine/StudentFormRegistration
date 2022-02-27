package qa.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class TestData {

    String
            firstName,
            lastName,
            email,
            gender,
            dateOfBirth,
            mobile,
            address,
            state,
            city;

    ArrayList<String>
            hobbies,
            subjects;
}
