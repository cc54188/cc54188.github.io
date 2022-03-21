import {Component, OnInit} from '@angular/core';
import {Lunch} from "../models/lunch.model";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-hello-angular',  // 包裝給其他元件
  templateUrl: './hello-angular.component.html',
  styleUrls: ['./hello-angular.component.scss']
})
export class HelloAngularComponent implements OnInit {
  // global變數
  title: string = "安哥拉便當";
  lunchList: Array<Lunch> = [];
  url = "http://localhost:8080/lunch";
  selectedLunch: Lunch = new Lunch();

  constructor(private httpClient: HttpClient) {
  }

  getLunchList () {
    this.httpClient.get(this.url + "/getAll").subscribe((res: any) =>{
      this.lunchList = res;
      // console.log(res);
    });
  }

  buyLunch(amount: string) {
    // 記得把字串轉數字
    const body = {"amount": amount};
    var buyUrl: string = this.url + "/buy/" + this.selectedLunch.id.toString();
    this.httpClient.put(buyUrl, body).subscribe((res: any) =>{
      this.getLunchList();
    });
  }

  changeSelectedLunch(event: any){
    console.log(this.selectedLunch);
  }

  ngOnInit(): void {
    this.getLunchList();
  }
}
