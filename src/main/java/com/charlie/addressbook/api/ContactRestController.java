package com.charlie.addressbook.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "${charlie.addressbook.origins:*}", allowedHeaders = "${charlie.addressbook.allowed-headers:*}")
public class ContactRestController
{
}
