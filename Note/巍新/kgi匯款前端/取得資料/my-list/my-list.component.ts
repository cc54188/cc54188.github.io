import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {GlobalDataService} from "../@core/shared/service/global.service";
import {FormBuilder} from "@angular/forms";
import {RouteUiService} from "../@core/shared/service/route-ui.service";
import {SlideDialogService} from "../@core/shared/service/slide-dialog.service";
import {ResizeService} from "../@core/shared/service/resize.service";
import {HttpClient} from "@angular/common/http";
import {SpinnerService} from "../@core/shared/service/spinner.service";
import {TradeModel} from "../@core/shared/model/trade.model";

@Component({
  selector: 'app-my-list',
  templateUrl: './my-list.component.html',
  styleUrls: ['./my-list.component.scss']
})
export class MyListComponent implements OnInit {

  tradeList: Array<TradeModel> = [];

  constructor(protected routingService: RouteUiService,
              protected dialogService: SlideDialogService,
              protected spinnerService: SpinnerService,
              protected resizeService: ResizeService,
              protected fb: FormBuilder,
              protected router: Router,
              protected activatedRoute: ActivatedRoute,
              protected gd: GlobalDataService,
              private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.getList();
  }

  getList(): void {
    const body = {"idno": this.gd.idno};
    this.httpClient.post('atm/getByIdno', body).subscribe((res: any) =>{
      this.tradeList = res;
    })
  }

  goMain(): void {
      this.router.navigate(['/']);
  }
}
