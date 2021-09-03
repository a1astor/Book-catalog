//package com.publishing.house.bookcatalog.services;
//
//import java.util.List;
//
//import org.junit.Rule;
//import org.testng.annotations.Test;
//
//@Test
//public class AuthorServiceTest {
//
//    public void getAllAuthorsTest() {
//        try (EmbeddedPostgres pg = EmbeddedPostgres.start();
//             Connection c = pg.getPostgresDatabase().getConnection()) {
//
//            Statement s = c.createStatement();
//            ResultSet rs = s.executeQuery("SELECT 1");
//            assertTrue(rs.next());
//            assertEquals(1, rs.getInt(1));
//            assertFalse(rs.next());
//
//        } catch (Exception e) {
//            System.out.print(e);
//            fail();
//        }
//    }
//
//}
