import {Component, OnInit} from "@angular/core";
import {Shortcut} from "./shortcut.model";
import {ActivatedRoute} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
    selector: 'app-new-link',
    templateUrl: './new-link.component.html',
    styleUrls: ['./new-link.component.scss']
})

export class BackLinkComponent implements OnInit{
    shortcut: Shortcut;
    shortUrl: string = "";

    constructor(private activatedRoute: ActivatedRoute, private http: HttpClient) {
        console.log(this.activatedRoute);

        this.shortUrl = this.activatedRoute.snapshot.paramMap.get('shortUrl');
    }

    ngOnInit(): void {
        console.log('back短:', this.shortUrl);
        this.http.get('/redirect').subscribe((res: any) => {

        })
    }
}