import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

@Pipe({
  name: 'link'
})
export class LinkPipe implements PipeTransform {

  constructor(private _domSanitizer:DomSanitizer){}

  transform(value: Array<any>): any {

    const link=`< a href=${value}>${value}</a>`;
    return this._domSanitizer.bypassSecurityTrustHtml(link);

    
  }

}
