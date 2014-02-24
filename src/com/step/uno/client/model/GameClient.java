package com.step.uno.client.model;

import com.step.communication.channel.MessageChannel;
import com.step.uno.messages.*;
import com.step.uno.model.Card;
import com.step.uno.model.Colour;

public class GameClient  {

    private MessageChannel channel;

    public GameClient(MessageChannel channel) {
        this.channel = channel;
    }

    public void sendIntroduction(String playerName) {
        channel.send(new Introduction(playerName));
    }

//    public void play(Card card){
//        System.out.println("Card played");
//        channel.send(new PlayCardAction(card));
//    }

    public void play(Card card, Colour newColour){
        //dont allow WildDraw4 when running colour is present
        //dont allow colour change to last card when heading to last card
        channel.send(new PlayCardAction(card,newColour));
     }
    public void informNoActionOnDrawnCard(){
        channel.send(new NoActionOnDrawnCard());
    }

    public void draw(){
        channel.send(new DrawCardAction());
    }

    public void drawTwo(){
        channel.send(new DrawTwoCardAction());
    }

    public void declareUno(){
        channel.send(new DeclareUnoAction());
    }

    public void catchUno(int playerIndex){
        channel.send(new CatchUnoAction( playerIndex));
    }
}
