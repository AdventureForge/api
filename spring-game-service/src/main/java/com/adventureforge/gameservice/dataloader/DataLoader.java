package com.adventureforge.gameservice.dataloader;

import com.adventureforge.gameservice.entities.Author;
import com.adventureforge.gameservice.entities.Illustrator;
import com.adventureforge.gameservice.entities.Writer;
import com.adventureforge.gameservice.repositories.IllustratorRepository;
import com.adventureforge.gameservice.repositories.WriterRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.UUID;

@Slf4j
@Transactional
@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final WriterRepository writerRepository;
    private final IllustratorRepository illustratorRepository;

    @Override
    public void run(String... args) throws Exception {

        Writer writer1 = Writer.builder()
                .uuid(UUID.randomUUID())
                .firstName("john")
                .lastname("doe")
                .pseudo("toto")
                .build();

        this.writerRepository.save(writer1);

        Writer writer2 = Writer.builder()
                .uuid(UUID.randomUUID())
                .firstName("jane")
                .lastname("doe")
                .pseudo("tata")
                .build();

        this.writerRepository.save(writer2);

        Illustrator illus1 = Illustrator.builder()
                .uuid(UUID.randomUUID())
                .firstName("albert")
                .lastname("dupont")
                .pseudo("al")
                .build();

        this.illustratorRepository.save(illus1);

        Illustrator illus2 = Illustrator.builder()
                .uuid(UUID.randomUUID())
                .firstName("jeanne")
                .lastname("dofour")
                .pseudo("jaja")
                .build();

        this.illustratorRepository.save(illus2);

        Author author = this.writerRepository.findById(1).get();

        log.info("Author found : {}", author.toString());
    }
}
