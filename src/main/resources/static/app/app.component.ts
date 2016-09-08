import {Component, OnInit} from "@angular/core";
import {Http} from "@angular/http";
import {IIssues} from "./issue.component";
import {IBazus} from "./bazus.component";

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


    constructor(private http: Http) {
    }

    private getAllIssueField(): void {
        this.hideComponent();

        if(this.dataRedmine.length==0){
            this.pobierzDaneZRedmine();
            console.info("Pobieram dane z Redmine");
        }
        this.pokazWszystkieIssue = true;

    }

    private getIssueField(idIssie: String): void {
        this.pokazDlaIssue = true;
        this.dataRedmine = [];
        this.http.get('/getFieldFromIssue?idIssue=' + idIssie)
            .subscribe(
                data => {
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
        this.hideComponent();

        if(this.dataBazus.length==0) {
            this.pobierzWersjeBazusa();
            console.info("Pobieram wersje Bazusa");
        }

        this.pokazWersjeBazusa = true;
    }

    private pobierzWersjeBazusa(): void{
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


    private hideComponent(): void {
        this.pokazDlaIssue = false;
        this.pokazWersjeBazusa = false;
        this.pokazWszystkieIssue = false;
        this.pokazPolaDlaIssue = false;
        this.dataRedmine = [];
        this.dataBazus = [];

    }

    private pokazSzczegolyWersjiBazusa(item): void {
        item.czyPokazac = !item.czyPokazac;
        if(item.czyPokazac == true) {
            this.dataRedmine = [];
            this.pobierzDaneZRedmineDlaWersjiBazusa(item.idWersja, item.idParent);
        }else{
            this.dataRedmine = [];
        }
    }

    private showIssueInfo(): void {
        this.hideComponent();
        this.pokazPolaDlaIssue = true;
    }

    private pobierzDaneZRedmine() {
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

    private pobierzDaneZRedmineDlaWersjiBazusa(idWersja, idParent) {
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

    ngOnInit(): void {
        console.log('Zaczynam prace');

    }

}


