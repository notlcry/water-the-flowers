package barry;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

    @Autowired
    private Server server;


    @Override
    public void run(String... args) throws Exception {
        // Start the clock
        long start = System.currentTimeMillis();

        // Kick of multiple, asynchronous lookups
        Future page1 = server.start();


        // Print results, including elapsed time
        logger.info("Elapsed time: " + (System.currentTimeMillis() - start));
//        logger.info("--> " + page1.get());

    }

}