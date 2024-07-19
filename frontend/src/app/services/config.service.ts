import { Injectable } from '@angular/core';
import { ApplicationRoutes } from '../app-main-rules/routes.enum';

@Injectable({
  providedIn: 'root'
})
export class ConfigService {
  public readonly baseApiUrl = ApplicationRoutes.BaseApiUrl;
}
