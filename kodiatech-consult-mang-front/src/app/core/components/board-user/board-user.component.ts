import { Component, OnInit } from '@angular/core';
import { ConsultantService } from 'src/app/features/services/consultant.service';
import { EventBusService } from '../../_shared/events/event-bus.service';
import { EventData } from '../../_shared/events/event.class';

@Component({
  selector: 'app-bord-user',
  templateUrl: './board-user.component.html',
  styleUrls: ['./board-user.component.scss']
})
export class BoardUserComponent implements OnInit {
  content?: string;

  constructor(private userService: ConsultantService,private eventBusService: EventBusService) { }

  ngOnInit(): void {
    this.userService.getUserBoard().subscribe(
      data => {
        this.content = data;
      },
      err => {
        this.content = err.error.message || err.error || err.message;

        if (err.status === 403)
          this.eventBusService.emit(new EventData('logout', null));
      }
    );
  }
  }


