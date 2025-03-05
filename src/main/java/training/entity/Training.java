package training.entity;

import lombok.Getter;
import lombok.Setter;
import training.TrainingType;

import java.util.HashSet;
import java.util.Set;

@Getter
public class Training {

    @Setter
    private Long id;

    private final String name;

    private final TrainingType trainingType;

    private final Set<Long> enrollments = new HashSet<>();

    public Training(String name, TrainingType trainingType) {
        this.name = name;
        this.trainingType = trainingType;
    }

    public static Training announce(String name, TrainingType trainingType) {
        return new Training(name, trainingType);
    }

    public void enroll(Set<Long> employeeIds) {
        enrollments.addAll(employeeIds);
    }
}
