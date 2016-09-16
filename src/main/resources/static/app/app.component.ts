import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {IIssues} from "./issue.component";
import {IBazus} from "./bazus.component";
import {IUser} from "./user.component";

@Component({
    selector: 'my-app',
    templateUrl: 'app/app.component.html',
    styleUrls: ['app/app.component.css']

})
export class AppComponent implements OnInit {

    private dataRedmine: IIssues[] = [];
    private dataBazus: IBazus[] = [];
    private pokazDlaIssue = false;
    private pokazPolaDlaIssue = false;
    private pokazWersjeBazusa = false;
    private pokazWszystkieIssue = false;
    private ladujeDaneShow = false;
    private user: IUser;
    private czyAdmin = false;
    private username: String;


    constructor(private http: Http) {
        this.http.get('/secure/getUserDetails')
            .subscribe(
                data => {
                    this.user = data.json() as IUser;
                    this.czyAdmin = this.user.czyAdmin;
                    this.username = this.user.username;
                    console.info(data);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );
    }

    private getAllIssueField(): void {
        this.hideComponent();

        if(this.dataRedmine.length==0){
            this.ladujeDaneShow = true;
            console.info(this.ladujeDaneShow);
            this.pobierzDaneZRedmine();


            console.info("Pobieram dane z Redmine");
        }
        this.pokazWszystkieIssue = true;

    }

    private getIssueField(idIssie: String): void {
        this.pokazDlaIssue = true;
        this.dataRedmine = [];
        this.ladujeDaneShow = true;
        this.http.get('/redmine/getFieldFromIssue?idIssue=' + idIssie)
            .subscribe(
                data => {
                    this.dataRedmine.push(data.json() as IIssues);
                    this.ladujeDaneShow = false;
                    console.info(this.dataRedmine);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );

    }

    private getBazusVersion(): void {
        this.hideComponent();

        if(this.dataBazus.length==0) {
            this.ladujeDaneShow = true;
            this.pobierzWersjeBazusa();
            console.info("Pobieram wersje Bazusa");
        }

        this.pokazWersjeBazusa = true;
    }

    private pokazSzczegolyWersjiBazusa(item): void {
        item.czyPokazac = !item.czyPokazac;
        if(item.czyPokazac == true) {
            this.dataRedmine = [];
            this.ladujeDaneShow = true;
            this.pobierzDaneZRedmineDlaWersjiBazusa(item.idWersja, item.idParent);

        }else{
            this.dataRedmine = [];
        }
    }

    private showIssueInfo(): void {
        this.hideComponent();
        this.pokazPolaDlaIssue = true;
    }

    private pobierzWersjeBazusa(): void{
        this.http.get('/bazus/getBazusVersion')
            .subscribe(
                data => {
                    this.dataBazus = data.json() as IBazus[];
                    this.ladujeDaneShow=false;
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );
    }

    private pobierzDaneZRedmine() {
        this.http.get('/redmine/getFieldFromAllIssues')
            .subscribe(
                data => {
                    this.dataRedmine = data.json() as IIssues[];
                    this.ladujeDaneShow=false;
                    console.info(data);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );
    }

    private pobierzDaneZRedmineDlaWersjiBazusa(idWersja, idParent) {
        console.info('Pobieram '+idParent+' '+idWersja);
        this.http.get('/redmine/getFieldFromAllIssues')
            .subscribe(
                data => {
                    this.dataRedmine = data.json() as IIssues[];
                    this.ladujeDaneShow=false;
                    console.info(data);
                },
                err => {
                    console.error('An error occurred', err);
                    alert('An error occurred!!!');
                }
            );
    }

    private hideComponent(): void {
        this.pokazDlaIssue = false;
        this.pokazWersjeBazusa = false;
        this.pokazWszystkieIssue = false;
        this.pokazPolaDlaIssue = false;
        this.dataRedmine = [];
        this.dataBazus = [];

    }



    ngOnInit(): void {
        console.log('Zaczynam prace');


    }

}


