package cmcc.core.book.service;
import org.springframework.stereotype.Service;
import cmcc.common.service.SimpleCurdService;
import cmcc.core.book.entity.Book;
@Service
public class BookService extends SimpleCurdService<Book, Long> {
}
