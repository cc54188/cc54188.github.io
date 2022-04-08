import {Component, OnInit} from "@angular/core";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";
import {Shortcut} from "./shortcut.model";

@Component({
    selector: 'app-new-link',
    templateUrl: './new-link.component.html',
    styleUrls: ['./new-link.component.scss']
})
export class NewLinkComponent implements OnInit {


    shortcut: Shortcut;
    shortUrl = "";
    longUrl = "";
    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) {
        console.log(this.activatedRoute);

        this.shortUrl = this.activatedRoute.snapshot.paramMap.get('shortUrl');
        console.log("shortUrl:",this.shortUrl);

    }

    query(){
        // shortUrl
    }
    // ngOnInit(): void {
    //     let id= JSON.stringify(this.route.snapshot.paramMap.get('id'));
    //     this.product_id=id
    // }
    ngOnInit(): void {
        this.http.post('/shortcut/link', this.shortUrl).subscribe((res: any) => {
            this.shortcut = res;
            console.log("res: ", res);
            this.longUrl = this.shortcut.longUrl;

            window.open(this.longUrl, '_blank');
        })
    }
}