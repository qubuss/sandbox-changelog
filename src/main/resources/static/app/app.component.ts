import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {IIssues} from "./issue.component"
import {IBazus} from "./bazus.component"

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/app.component.css']

})
export class AppComponent implements OnInit {

    private dataRedmine: IIssues[] = [];
    private dataBazus: IBazus[] = [];
    private pokazDlaIssue = false;


    constructor(private http: Http) {
    }

    private getAllIssueField(): void {


        this.pokazDlaIssue = false;
        this.dataRedmine = [];
        this.dataBazus = [];

        this.http.get('/getFieldFromAllIssues')
            .subscribe(
                data => {
                    this.dataRedmine = data.json() as IIssues[];
                    console.info(data);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );

    }

    private getIssueField(idIssie: String): void {

        this.dataRedmine = [];
        this.dataBazus = [];

        this.http.get('/getFieldFromIssue?idIssue='+idIssie)
            .subscribe(
                data =>{
                    this.dataRedmine.push(data.json() as IIssues);
                    console.info(this.dataRedmine);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );

    }

    private getBazusVersion(): void {
        this.pokazDlaIssue = false;
        this.dataRedmine = [];
        this.dataBazus = [];

            this.http.get('/getBazusVersion')
                .subscribe(
                    data => {
                        this.dataBazus = data.json() as IBazus[];
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
        this.dataRedmine = [];
        this.pokazDlaIssue = true;
        this.dataBazus = [];

    }




}


