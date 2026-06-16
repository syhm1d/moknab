/**
 * @author Afiq Irfan / Naqyuddin
 * System-wide interface capturing operational kitchen preparation states.
 */
public interface Preparable {
    void startPreparation();
    void completePreparation();
    int getPrepTimeEstimation();
}