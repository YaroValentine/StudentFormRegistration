package qa.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.ArrayList;

@Data
@Accessors(chain = true)
public class PracticeFormData {

    String
            firstName,
            lastName,
            email,
            gender,
            dateOfBirth,
            number,
            address,
            state,
            city;

    ArrayList<String>
            hobbies,
            subjects;
}
