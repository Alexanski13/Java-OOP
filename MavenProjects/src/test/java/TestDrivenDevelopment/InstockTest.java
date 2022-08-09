package TestDrivenDevelopment;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class InstockTest {

    private Instock instock;

    @Before
    public void setUp() {
        this.instock = new Instock();
    }

    @Test
    public void testShouldBeZeroWhenInstockEmpty() {
        assertEquals(0, instock.getCount());
    }

    @Test
    public void testShouldBeFiveWhenInstockHasFiveProducts() {

        int productsCount = 5;

        addProductsToInstock(productsCount);
        assertEquals(productsCount, instock.getCount());
    }

    private Product[] addProductsToInstock(int count) {

        Product[] arr = new Product[count];
        for (int i = 1; i <= count; i++) {
            Product product = new Product("product" + i, 10.00 + i, i);
            instock.add(product);
            arr[i - 1] = product;
        }

        return arr;
    }

    @Test
    public void testContainsShouldBeTrueOrFalseWhenProductPresentOrNot() {
        Product product = addProductsToInstock(5)[3];
        assertTrue(instock.contains(product));
        assertFalse(instock.contains(new Product("not present", 1, 1)));
    }

    @Test
    public void testFindReturnsTheCorrectProduct() {
        var expected = addProductsToInstock(10)[4];
        var actual = instock.find(4);
        assertNotNull(actual);
        assertEquals(expected.getLabel(), actual.getLabel());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWithNegativeIndex() {
        instock.find(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindShouldThrowWithIndexGreaterThanLast() {
        addProductsToInstock(2);
        instock.find(instock.getCount());
    }

    @Test
    public void testChangeQuantityShouldUpdateTheProduct() {
        Product product = new Product("test", 1, 10);
        instock.add(product);
        int expected = product.getQuantity() + 5;
        instock.changeQuantity(product.getLabel(), expected);

        assertEquals(expected, product.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityThrowsWhenNoProductWithLabel() {
        instock.changeQuantity("non present", 10);
    }

    @Test
    public void testFindByLabelShouldReturnTheCorrectProduct() {
        var expected = addProductsToInstock(10)[3];
        Product actual = instock.findByLabel(expected.getLabel());
        assertNotNull(actual);
        assertEquals(expected.getLabel(), actual.getLabel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelThrowsWhenNoProductWithLabel() {
        instock.findByLabel("non present");
    }

    @Test
    public void testFindFirstMostExpensiveProductsShouldReturnTheCorrectProducts() {
        int productsToTake = 10;
        var expected = Arrays.stream(addProductsToInstock(20))
                .sorted(Comparator.comparing(Product::getPrice).reversed())
                .limit(productsToTake)
                .collect(Collectors.toList());

        var iterable = instock.findFirstMostExpensiveProducts(productsToTake);

        var actual = assertIterableNotNullAndConvertToList(iterable);

        assertEquals(expected, actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsShouldThrowWhenThereAreLessProducts() {
        addProductsToInstock(10);
        instock.findFirstMostExpensiveProducts(11);
    }

    private List<Product> assertIterableNotNullAndConvertToList(Iterable<Product> iterable) {
        assertNotNull(iterable);

        List<Product> products = new ArrayList<>();

        iterable.forEach(products::add);

        return products;
    }

}