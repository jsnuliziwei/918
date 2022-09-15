package com.gao.myspringboot.config;

import com.gao.myspringboot.controller.*;

import com.gao.myspringboot.controller.admin.*;
import com.gao.myspringboot.pojo.Word;
import org.springdoc.core.SpringDocUtils;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swagger3Config {
    static {
        SpringDocUtils.getConfig()
                .addRestControllers(BookController.class)
                .addRestControllers(CheckController.class)
                .addRestControllers(CollectionController.class)
                .addRestControllers(ListenController.class)
                .addRestControllers(LoginController.class)
                .addRestControllers(MainPageController.class)
                .addRestControllers(NoticeController.class)
                .addRestControllers(RegisterController.class)
                .addRestControllers(ScoreController.class)
                .addRestControllers(SearchController.class)
                .addRestControllers(UserController.class)
                .addRestControllers(WordController.class)
                .addSimpleTypesForParameterObject(Word.class)

                .addRestControllers(UpdateUserController.class)
                .addRestControllers(UpdateBookController.class)
                .addRestControllers(UpdateNoticeController.class)
                .addRestControllers(UpdatePrivilegeController.class)
                .addRestControllers(UpdateWordController.class)
                .addRestControllers(UpdateListenController.class);
    }
}
