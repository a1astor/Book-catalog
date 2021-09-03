//package com.publishing.house.bookcatalog;
//
//import org.junit.BeforeClass;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringRunner;
//
//public class AbstractTest {
//    @RunWith(SpringRunner.class)
//    @SpringBootTest(classes = Initialiser.class)
//    @TestPropertySource("classpath:application-test.properties")
//    public class AbstractIntegrationTest {
//
//        private static EmbeddedPostgres embeddedPostgres;
//
//        @BeforeClass
//        public static void initialise() throws Exception{
//            if(embeddedPostgres == null) {
//                //Create an instance of embedded postgress
//                embeddedPostgres = EmbeddedPostgres.builder()
//                        .setPort(5433).start();
//
//                try (Connection conn = embeddedPostgres.getPostgresDatabase().getConnection()) {
//                    Statement statement = conn.createStatement();
//                    statement.execute("CREATE DATABASE EXAMPLEDB");
//                }
//            }
//        }
//}
