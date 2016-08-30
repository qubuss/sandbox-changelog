import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/app.component.css']

})
export class AppComponent implements OnInit {

    private counter: Number;
    private data: IConnectionProperties;

    constructor(private http: Http) {
        this.counter = 0;
    }

    private addToCounter(value: Number): void {
        this.counter += value;
    }

    private fetch(): void {
        this.http.get('/showProperties')
            .subscribe(
                data => {
                    this.data = data.json() as IConnectionProperties;
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );
    }

    ngOnInit(): void {
        console.log('Component AppComponent is up and running');
    }
}

export interface IConnectionProperties {
    username: String,
    password: String,
    urlRedmine: String,
    urlChangelogGit: String,
    urlChangelogGit150: String
}
