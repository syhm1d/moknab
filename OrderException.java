/**
 * @author Haiqal Razeeq / Naqyuddin
 * Single unified system exception thrown when an order validation fails
 * or when customer point limits are violated.
 */
public class OrderException extends Exception {
    public OrderException(String message) {
        super(message);
    }
}