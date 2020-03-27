package com.sanction.template;

import com.sanction.template.authentication.TemplateAuthenticator;
import com.sanction.template.resources.TemplateResource;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TemplateModule.class})
public interface TemplateComponent {

  TemplateResource getTaskResource();

  TemplateAuthenticator getHurricaneAuthenticator();
}
