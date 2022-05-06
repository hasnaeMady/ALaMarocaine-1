//chef.service has methods for sending HTTP requests to the Apis
//this service will use angular HttpClient to send HTTP requests
//Services are used tu share single piece of code across multiple components.These services are used to hold business logic.
//Services are used to interact with the backend.
import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
/**
 * The HTTP client service offers the following major features:

The ability to request typed response objects.
Streamlined error handling.
Testability features.
Request and response interception.
 */
import {Observable} from 'rxjs';
import {Chef} from '../Model/chef.model';

const baseUrl='http://localhost:9390/api/chefs';//base url is the consistent part of your web address


@Injectable({ //this decorrator tells angular that the class is a service and can be injected into components that need that service.
                 //They can also inject other services as dependencies
  providedIn: 'root'//  this decorator has a provideIn property,which creates a provider for the service.                                                   
  //https://stackoverflow.com/questions/50848357/what-is-the-purpose-of-providedin-with-the-injectable-decorator-when-generating
})



export class ChefService {

  constructor(private http:HttpClient) { }//HttpClient service makes use of observables for all the transactions

  /**Angular makes use of observables as an interface to handle a variety of common asynchronous operations
   * You can define custom events that send observable output data from a child to a parent component.
The HTTP module uses observables to handle AJAX requests and responses.
The Router and Forms modules use observables to listen for and respond to user-input events
   */

/***
 * Observable in Angular is a feature that provides support for delivering messages between different parts of your single-page application. This feature is frequently used in Angular because it is responsible for handling multiple values, asynchronous programming in Javascript, and also event handling processes.
 */
  

  getAll(): Observable<Chef[]>{
    return this.http.get<Chef[]>(baseUrl);//HttpClient.get() method to fetch data from a server
  //the get() method takes two arguments;the endpoint URL  from which to fetch and 
  }


  get(chef_id:any): Observable<Chef> { //observables provide support for passing messages between parts of your application.. They are used frequently in Angular and are a technique for event handling, asynchronous programming, and handling multiple values.
     return this.http.get(`${baseUrl}/${chef_id}`);
  }

  create(data:any):Observable<any>{
    return this.http.post(baseUrl,data);
  }
  update(chef_id:any,data:any) :Observable<any> {
    return this.http.put(`${baseUrl}/${chef_id}`,data);
  }
  delete(chef_id:any):Observable<any>{
    return this.http.delete(`${baseUrl}/${chef_id}`);
  }
  deleteAll():Observable<any>{
    return this.http.delete(baseUrl);
  }
  findByNom(nom:any):Observable<Chef[]>{
    return this.http.get<Chef[]>(`${baseUrl}?nom=${nom}`);
  }




  
  //this function is implemented by hasnae to find chiefs by status weither true(chef de semaine)
  
  getChefS(chefDeSemaine:boolean):Observable<Chef>{
    return this.http.get(`${baseUrl}?chefDeSemaine=true`);
  }

}
