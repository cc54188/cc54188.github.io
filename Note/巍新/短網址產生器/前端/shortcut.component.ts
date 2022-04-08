import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Shortcut} from "./shortcut.model";
import {Router} from "@angular/router";
import {COMPONENT_AIO} from "../@core/shared/routing/aio.path";

@Component({
    selector: 'app-shortcut',
    templateUrl: './shortcut.component.html',
    styleUrls: ['./shortcut.component.scss']
})
export class ShortcutComponent implements OnInit {

    longUrl: string = "";

    shortUrl: string = "";

    shortcut: Shortcut = new Shortcut();

    constructor(private http: HttpClient, protected router: Router) {
    }

    ngOnInit(): void {
    }

    addUrl(url: string) {
        console.log('長', url);
        this.http.post("/shortcut/add", this.longUrl).subscribe((res: any)=>{
            console.log('短1:', res);

            this.shortcut = res;
            this.shortUrl = this.shortcut.shortUrl;
            console.log('短2:', this.shortUrl);
        })
    }

    goToLink(url: string) {
        window.open(url, "_blank");
    }

    goMain() {
        this.router.navigate([COMPONENT_AIO.MAIN]);
    }

}
