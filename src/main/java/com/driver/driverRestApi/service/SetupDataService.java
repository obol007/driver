package com.driver.driverRestApi.service;

import com.driver.driverRestApi.model.Tag;
import com.driver.driverRestApi.repository.TagRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("heroku")
public class SetupDataService implements CommandLineRunner {

    private final TagRepository tagRepository;

    public SetupDataService(@Qualifier("MySQL") TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        Tag tag = new Tag();
        tag.setName("jazda");
        tagRepository.save(tag);

        Tag tag1 = new Tag();
        tag1.setName("hamowanie");
        tagRepository.save(tag1);




    }
}
