import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {COMPONENT_AIO} from '../@core/shared/routing/aio.path';
import {RouteUiService} from '../@core/shared/service/route-ui.service';
import {SlideDialogService} from '../@core/shared/service/slide-dialog.service';
import {SpinnerService} from '../@core/shared/service/spinner.service';
import {ResizeService} from '../@core/shared/service/resize.service';
import {GlobalDataService} from '../@core/shared/service/global.service';
import {GenericService} from '../@core/shared/service/generic.service';
import {InterBankModel} from '../@core/shared/model/inter-bank.model';

@Component({
    selector: 'app-inter-bank-2-confirm',
    templateUrl: './inter-bank-2-confirm.component.html',
    styleUrls: ['./inter-bank-2-confirm.component.scss']
})
export class InterBank2ConfirmComponent implements OnInit {

    data: InterBankModel;

    constructor(protected routingService: RouteUiService,
                protected dialogService: SlideDialogService,
                protected spinnerService: SpinnerService,
                protected resizeService: ResizeService,
                protected fb: FormBuilder,
                protected router: Router,
                protected activatedRoute: ActivatedRoute,
                protected gd: GlobalDataService,
                protected genericService: GenericService) {

                this.data = this.gd.interBankList;
                this.data.type = 4;  // 只好硬塞這裡，之後再改
                
        console.log('確認list: ', this.gd.interBankList);
        console.log('確認data:', this.data);
    }

    ngOnInit() {
        
    }

    saveToAll(data: any) {  // 裡面都是後來加的
        if(!this.gd.tradeList) {  // 若沒有tradeList
            this.gd.tradeList = [];  // 就建一個
        }
        let tradeList = this.gd.tradeList;
        tradeList.push(...data);  
        this.gd.tradeList = tradeList;  // 為何不直接塞進這裡
    }

    goMain() {
        console.log("測試");
        this.saveToAll(this.data);
        this.gd.clearInterBankList();
        console.log("匯款: " + this.data);
        // this.router.navigate(['/'+COMPONENT_AIO.MAIN]);
    }

    /**
     * Go to last page to confirm and save data
     */
    goNext() {
        this.saveToAll(this.data);
        this.gd.clearInterBankList();
        this.router.navigate(['/'+ COMPONENT_AIO.FINISH]);
    }

    goBack() {
        this.router.navigate(['/'+ COMPONENT_AIO.INTER_BANK_2]);
    }

}
