import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;
    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant1 = Mockito.spy(restaurant);
        LocalTime time1 = LocalTime.parse("11:00:00");
        Mockito.when(restaurant1.getCurrentTime()).thenReturn(time1);
        assertTrue(restaurant1.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE
        Restaurant restaurant2  = Mockito.spy(restaurant);
        LocalTime time2 = LocalTime.parse("23:00:00");
        Mockito.when(restaurant2.getCurrentTime()).thenReturn(time2);
        assertFalse(restaurant2.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {


        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void selected_items_should_display_the_total_amount_of_items() throws itemNotFoundException {
        List<String> clickedItems = new ArrayList<String>();
        clickedItems.add("Sweet corn soup");
        clickedItems.add("Vegetable lasagne");
        assertEquals(388,restaurant.getOrderTotal(clickedItems));
    }
    @Test
    public void selected_items_should_display_total_amount_remove_one() throws itemNotFoundException {
        List<String> clickedItems = new ArrayList<String>();
        clickedItems.add("Sweet corn soup");
        assertEquals(119,restaurant.getOrderTotal(clickedItems));
    }
    @Test
    public void nothing_clicked_should_return_zero() throws itemNotFoundException {
        List<String> clickedItems = new ArrayList<String>();
        assertEquals(0, restaurant.getOrderTotal(clickedItems));
    }
}
