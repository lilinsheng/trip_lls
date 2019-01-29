package io.github.dunwu.spring.websocket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class User {

	private Long id;

	private String name;

	private String password;

	private String headImgUrl;

}
