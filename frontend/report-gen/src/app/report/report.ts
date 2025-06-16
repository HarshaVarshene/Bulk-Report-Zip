import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatCheckboxModule, MatCheckboxChange } from '@angular/material/checkbox';
import { MatRadioModule } from '@angular/material/radio';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSnackBar, MatSnackBarModule } from '@angular/material/snack-bar';

@Component({
  selector: 'app-report',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    MatCheckboxModule,
    MatRadioModule,
    MatButtonModule,
    MatCardModule,
    MatProgressSpinnerModule,
    MatSnackBarModule
  ],
  templateUrl: './report.html',
  styleUrls: ['./report.css']
})
export class Report {
  entities = ['customer', 'project'];
  selectedEntities: { [key: string]: boolean } = {};
  selectedFormats: { [key: string]: string } = {};
  loading = false;

  constructor(private http: HttpClient, private snackBar: MatSnackBar) {}

  toggleEntity(entity: string, event: MatCheckboxChange) {
    this.selectedEntities[entity] = event.checked;
  }

  downloadReports() {
    const payload = Object.entries(this.selectedEntities)
      .filter(([entity, isChecked]) => isChecked && this.selectedFormats[entity])
      .map(([entity]) => ({ entity, format: this.selectedFormats[entity] }));

    if (payload.length === 0) {
      this.snackBar.open('Please select at least one entity and format.', 'Close', { duration: 3000 });
      return;
    }

    this.loading = true;

    this.http.post('http://localhost:8080/api/reports/download', payload, { responseType: 'blob' })
      .subscribe(blob => {
        this.loading = false;
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement('a');
        a.href = url;
        a.download = 'bulk_reports.zip';
        a.click();
        window.URL.revokeObjectURL(url);
        this.snackBar.open('Download successful!', 'Close', { duration: 3000 });
      }, error => {
        this.loading = false;
        console.error('Failed to download reports', error);
        this.snackBar.open('Error downloading reports. Please try again.', 'Close', { duration: 3000 });
      });
  }
}