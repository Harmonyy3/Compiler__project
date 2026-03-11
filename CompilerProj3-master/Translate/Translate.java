// package Translate;

// import Frame.Frame;


// /**
//  * procEntryExit: Wrap a function body with prologue/epilogue.
//  * 
//  * @param frame the function's stack frame
//  * @param body  the IR for the function body (Tree.Exp or Tree.Stm)
//  */
// public class Translate {

//     public static void procEntryExit(Frame frame, Exp body) {
//         // 1️⃣ Convert Exp to Stm if needed
//         Stm bodyStm;
//         if (body instanceof Stm) {
//             bodyStm = (Stm) body;
//         } else {
//             bodyStm = new Nx(body);  // Wrap Exp as a statement
//         }

//         // 2️⃣ Add prologue (save callee-saved registers, adjust SP)
//         // Typically this is done in codegen using frame info.
//         // For now we just annotate:
//         System.out.println("Prologue for function: " + frame.name);

//         // 3️⃣ Function body already in bodyStm
//         // Nothing to modify here if your IR handles it

//         // 4️⃣ Add epilogue (restore SP, return)
//         System.out.println("Epilogue for function: " + frame.name);

//         // 5️⃣ Normally we would create a ProcFrag or similar object
//         //    to store the frame + bodyStm for code generation.
//         //    Example:
//         //    Frag f = new ProcFrag(bodyStm, frame);
//         //    codeGenList.add(f);

//         // Minimal version: just print that we finalized the function
//         System.out.println("Finished procEntryExit for " + frame.name);
//     }
// }
