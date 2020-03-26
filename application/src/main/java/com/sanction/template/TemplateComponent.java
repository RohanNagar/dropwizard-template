package com.sanction.template;

import com.sanction.template.authentication.TemplateAuthenticator;
import com.sanction.template.resources.TaskResource;

import dagger.Component;

import javax.inject.Singleton;

@Singleton
@Component(modules = {TemplateModule.class})
public interface TemplateComponent {

  TaskResource getTaskResource();

  TemplateAuthenticator getHurricaneAuthenticator();
}
