package ra.crud.model;


@NoArgsConstruct
@AllArgsConstruct
@Getter
@Setter

public class Book {
    private int bookId;
    private String bookName;
    private float price;
    private boolean status;
}
