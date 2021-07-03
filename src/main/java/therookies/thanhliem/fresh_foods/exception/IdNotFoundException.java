package therookies.thanhliem.fresh_foods.exception;

public class IdNotFoundException extends RuntimeException {
    public IdNotFoundException(Long id, Object o) {
        super("Could not find item by id = "+id +" in "+o.getClass().getName()) ;
    }
}
