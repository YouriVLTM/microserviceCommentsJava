package com.example.demo.model;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class CommentUnitTest {

    @Test
    void key() {
        Date nu = new Date(System.currentTimeMillis());
        Comment comment = new Comment("test","subti","hallo ik ben youri","youri@hotmail.com","urlimage", nu);
        assertEquals(DigestUtils.sha256Hex("test" + "youri@hotmail.com" +nu), comment.getKey());
    }


}