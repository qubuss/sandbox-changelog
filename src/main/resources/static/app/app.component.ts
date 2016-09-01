import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/app.component.css']

})
export class AppComponent implements OnInit {

    private data: IIssues[] = [];
    private czy = false;


    constructor(private http: Http) {
    }

    private getAllIssueField(): void {
        this.http.get('/getFieldFromAllIssues')
            .subscribe(
                data => {
                    this.data = data.json() as IIssues[];
                    console.info(data);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );

    }

    private getIssueField(idIssie: String): void {
        this.http.get('/getFieldFromIssue?idIssue='+idIssie)
            .subscribe(
                data =>{
                    this.data.push(data.json() as IIssues);
                    console.info(this.data);
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

    private show(): void {
        this.czy = true;
    }
}

export interface IIssues {
    issueId: String,
    subject: String,
}

