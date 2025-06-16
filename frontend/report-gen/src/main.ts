import { bootstrapApplication } from '@angular/platform-browser';
//import { appConfig } from './app/app.config';
import { App } from './app/app';
import { Report } from './app/report/report';


bootstrapApplication(Report)
  .catch((err) => console.error(err));
